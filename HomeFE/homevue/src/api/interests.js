import { apiInstance } from "./index.js";

const api = apiInstance();

async function interestList(id, success, fail) {
  await api.get(`/interests/${id}`).then(success).catch(fail);
}

async function add(params, success, fail) {
  await api.get(`/interests/${params.aptCode}/${params.id}/interestAdd`).then(success).catch(fail);
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

export { interestList, add };
