import {Auth, google} from "googleapis"
import {getInput, info, setFailed} from "@actions/core"
import _ from "lodash"

const GOOGLE_SHEET_ID = "sheet_id"
const GOOGLE_SERVICE_ACCOUNT = "google_token"
const COLUMN_BASE_OFFSET = "column_offset"
const USER_COLUMN = "user_column"
const GITHUB_USER = "github_user"
const HOMEWORK_NUMBER = "homework_number"
const MARK = "mark"

const getOrDefault = (name: string, defaultValue: string | number): string | number => {
    let input = getInput(name)
    return input ? input : defaultValue
}

const equalsIgnoreCase = (a: string, b: string) => _.eq(a.toLocaleLowerCase(), b.toLocaleLowerCase())

const updateCell = async (sheetId: string, range: string, mark: string, credentials: Auth.JWT) => {
    return await sheets.spreadsheets.values
        .update({
            auth: credentials,
            spreadsheetId: sheetId,
            range: range,
            valueInputOption: "USER_ENTERED",
            requestBody: {
                values: [[mark]]
            }
        })
        .then(r => r.data)
        .catch(e => setFailed(e.message))
}

const findRow = async (githubUser: string, columnName: string, sheetId: string, credentials: Auth.JWT) => {
    const response = await sheets.spreadsheets.values
        .get({
            auth: credentials,
            spreadsheetId: sheetId,
            range: `${columnName}1:${columnName}100`,
            majorDimension: "COLUMNS",
            valueRenderOption: "FORMATTED_VALUE"
        })
        .then(r => r.data)
        .catch(e => setFailed(e.message))

    const logins = _.get(response, "values[0]")
    const index = _.findIndex(logins, (v: string) => equalsIgnoreCase(v, githubUser))

    if (index === -1) {
        setFailed(`User ${githubUser} not found in sheet`)
    }
    return index + 1
}

const sheets = google.sheets("v4")

const githubUser = getInput(GITHUB_USER, {required: true})

info(`Github user ${githubUser}`)

const sheetId = getInput(GOOGLE_SHEET_ID, {required: true})
const googleApiKey = JSON.parse(getInput(GOOGLE_SERVICE_ACCOUNT, {required: true}))
const mark = getOrDefault(MARK, "'+") as string
const homeworkNumber = getOrDefault(HOMEWORK_NUMBER, 1) as number
const columnOffset = getOrDefault(COLUMN_BASE_OFFSET, "F") as string
const userColumn = getOrDefault(USER_COLUMN, "D") as string

const credentials = new Auth.JWT({
    email: googleApiKey.client_email,
    key: googleApiKey.private_key,
    scopes: [
        "https://www.googleapis.com/auth/drive",
        "https://www.googleapis.com/auth/drive.file",
        "https://www.googleapis.com/auth/spreadsheets"
    ]
})

const row = await findRow(githubUser, userColumn, sheetId, credentials)
const column = String.fromCharCode(columnOffset.toUpperCase().charCodeAt(0) + homeworkNumber - 1)
const cell = `${column}${row}`

info(`Mark '${mark}' for student ${githubUser} at cell ${cell}`)

await updateCell(sheetId, `${cell}:${cell}`, mark, credentials)
