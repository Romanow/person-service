"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const googleapis_1 = require("googleapis");
const sheets = googleapis_1.google.sheets("v4");
const sheetId = "1BT5iLgERiWUPPn4gtOQk4KfHjVOTQbUS7ragAJrl6-Q";
const range = "B80:B80";
const scopes = [
    "https://www.googleapis.com/auth/drive",
    "https://www.googleapis.com/auth/drive.file",
    "https://www.googleapis.com/auth/spreadsheets"
];
const json = {
    type: "service_account",
    project_id: "github-classroom-autograder",
    private_key_id: "1b8d5685103f848f5549c101fb3c892d2b57ecc6",
    private_key: "-----BEGIN PRIVATE KEY-----\nMIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQDeI/quiJ7UYU4H\n3+y8SkBtZOWvjceF+QbhiXU6QQgY3q5l+FqoR1AdmBiVfcRvraZtjOZ75vtooGIA\n/lCWQS7mRav36Sczkk7h5RFZacYPBtjf2ATjQj7PX3K/dzwX0JuWPyLliY4xVeZ7\n3UHuemZn6f4d15zNUCL7bw4w5EnnSRMP2KqmGZmPV1FDD5uHVhnqUTxtT3FLyB09\nMM5YcfX1QNQEAxztVoW2qlyYTcYnM18Ym+T9vQJ0YTpfBTN+zqgTLLE6+cqq5Hok\nP848ZLDE+hSf5PVf13Sq0cxowP/Q74XvfVNhygyt4oRDSfehPZHRZVLVMSLFSOiO\n2t5cNmGDAgMBAAECggEAFbGSz3elId2CJs+4nqdrr6M9BmNvWvHiY6UuZGrUanAD\nZ7mUyj6d9X3p1140ZamiNAGgwp816dZRK9f/hmTwOmm5wm4BDOQavaxxViI3xuDm\nzhlvr21D6rGKExEHC9ZXU27FWYA7pC1yOEzNHbJ+DLjeabX+6KgM+oaybpY6HL1+\nFpilltRa/pkQ2uDDoG66HQrdwbRynKlH9tMVpDO8XVQwZKtder6znDTrjEB4Mal4\n4Ny+LFFB2RpyV5fEJwpOdBCiSlOrN8e7ZYKl0zfUuMRJOfP24vNOpULkIzf9iiQw\nXy3zVoxz70Rn8nKwKfGQk7wAR+8u+Ivi9NhvyUMYcQKBgQDxSfBXUCLXHs7dYqDs\nsVYeTaaORrsldC97/cV+4ssiPq8j2zlQx0MiHtx2zJbouXt+zlFvDnQBij5KJHbX\nRVQHBxJRoZCFSk9pauXL+XGDRq1HtgM9geZt3FQ4cLMIKCGcMMvC/T2FzCAw5s9x\n3digal8Ct20+ypAyYCV528Ry0QKBgQDrrywLAw/FyAdAUEjgiI+mjockf4P1FH+4\nBd2Kx6eJo6CgIc+7OYqUEZfebde9VmYd3KRnUcCG9Ok8Q+KFhkdWXGySBrQNzrOQ\nauzaAggzFtzFMpQvyYjHX0dUg0yVBfq88CBHy3D34HV1hP+xz2ekX1rllOWX7O12\np6LoZaMcEwKBgD9+s45meiSd6NX9RFfE+uKSkIfUOR9MCcrZJwWS7N2gHTqUT+rf\n5NyvvlhM0lF/re3aoTdWWFSnamjcW6GejtQimkF9AKjyux8EkGAyzho7mRHL5eJj\nsp2qY0dO8O3ukZmmhZhUR8JTywzTUDeop37fPw1Z85eYeoWnI3WDzZhhAn95hdkv\n1GRNltYmUz9AhymKt/L23skrlWZFfMC+zjOUxMoNx2FVKFZMAsZL6ldTSTleLGaY\n345mB+I7MMWcE6C05vMUWGB+vhXYYUm/H00bCq7K/0lpWoEsS/R7nzfdnzeqNCZw\n3nVhWls2PV2aUVcfagt0VVINBB1tEQLu0XlJAoGAEHkwAksinrqbZRXXmdJRGu3m\nB1jsfneeiKzPnQ25QpkvgjmIdiFEpx122Xnh1pC1Pyauu1wXTKdDSYKfXQm3x2Ys\nFmNBVRDjRHZVUi1KHLFUhNiXwuHfsBa7iCbnKVPW7QQ3oc2NVzu591rPM3hXlHIA\nKg5dVHMMeBwTsLdmMwc=\n-----END PRIVATE KEY-----\n",
    client_email: "github-autograder-account@github-classroom-autograder.iam.gserviceaccount.com",
    client_id: "116379512639205522475",
    auth_uri: "https://accounts.google.com/o/oauth2/auth",
    token_uri: "https://oauth2.googleapis.com/token",
    auth_provider_x509_cert_url: "https://www.googleapis.com/oauth2/v1/certs",
    client_x509_cert_url: "https://www.googleapis.com/robot/v1/metadata/x509/github-autograder-account%40github-classroom-autograder.iam.gserviceaccount.com"
};
const credentials = new googleapis_1.Auth.JWT({
    email: json.client_email,
    key: json.private_key,
    scopes: scopes
});
get(credentials)
    .then(r => console.log(r.data))
    .catch(e => console.error(e));
function get(auth) {
    return __awaiter(this, void 0, void 0, function* () {
        return yield sheets.spreadsheets.values.update({
            auth: auth,
            spreadsheetId: sheetId,
            range: range,
            valueInputOption: "USER_ENTERED",
            requestBody: {
                values: [[Date.now()]]
            }
        });
    });
}
