import { apiInstance } from "./index.js";

const api = apiInstance();

function freeListArticle(param, success, fail) {
api.get(`/freeboardlist`, { params: param }).then(success).catch(fail);
}

function freeWriteArticle(article, success, fail) {
api.post(`/freeboardwrite`, JSON.stringify(article)).then(success).catch(fail);
}

function freeGetArticle(boardid, success, fail) {
api.get(`/freeboardview/${boardid}`).then(success).catch(fail);
}

function freeModifyArticle(article, success, fail) {
api.put(`/freeboardupdate`, JSON.stringify(article)).then(success).catch(fail);
}

function freeDeleteArticle(boardid, success, fail) {
api.delete(`/freeboarddelete/${boardid}`).then(success).catch(fail);
}

export { freeListArticle, freeWriteArticle, freeGetArticle, freeModifyArticle, freeDeleteArticle };
