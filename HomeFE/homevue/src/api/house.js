import { apiInstance } from "./index.js";

const api = apiInstance();

function getHouseList(success, fail) {
api.get(`/home`).then(success).catch(fail);
}

function writeHouseDeal(house, success, fail) {
api.post(`/createHouse`, JSON.stringify(house)).then(success).catch(fail);
}

// function getArticle(announcementid, success, fail) {
// api.get(`/boardview/${announcementid}`).then(success).catch(fail);
// }

// function modifyArticle(article, success, fail) {
// api.put(`/boardupdate`, JSON.stringify(article)).then(success).catch(fail);
// }

// function deleteArticle(announcementid, success, fail) {
// api.delete(`/boarddelete/${announcementid}`).then(success).catch(fail);
// }

export { getHouseList,writeHouseDeal };
