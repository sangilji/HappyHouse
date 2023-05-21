import { apiInstance } from "./index.js";

const api = apiInstance();

function getInterests(id, success, fail) {
api.get(`/interests/${id}`).then(success).catch(fail);
}

// function writeArticle(article, success, fail) {
// api.post(`/boardwrite`, JSON.stringify(article)).then(success).catch(fail);
// }

// function getArticle(announcementid, success, fail) {
// api.get(`/boardview/${announcementid}`).then(success).catch(fail);
// }

// function modifyArticle(article, success, fail) {
// api.put(`/boardupdate`, JSON.stringify(article)).then(success).catch(fail);
// }

// function deleteArticle(announcementid, success, fail) {
// api.delete(`/boarddelete/${announcementid}`).then(success).catch(fail);
// }

export { getInterests, };
