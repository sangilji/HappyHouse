import { apiInstance } from "../index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

async function getHouseList(params, success, fail) {
  await api.get(`/home/search`,{params}).then(success).catch(fail);
}

async function getHouseList2(dong, success, fail) {
  await api.get(`/home/search/${dong}`,).then(success).catch(fail);
}

async function houseDeal(aptCode, success, fail) {
  await api.get(`/home/housedeal/${aptCode}`).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, getHouseList,getHouseList2, houseDeal };
