import { apiInstance } from "./index.js";

const api = apiInstance();

function reviewList(aptCode,success, fail) {
  api.get(`/home/review/${aptCode}`).then(success).catch(fail);
}
export { reviewList };