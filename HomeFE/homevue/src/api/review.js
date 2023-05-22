import { apiInstance } from "./index.js";

const api = apiInstance();

function reviewList(aptCode,success, fail) {
  api.get(`/home/review/${aptCode}`).then(success).catch(fail);
}

async function addReview(review,success, fail) {
  await api.post(`/home/review/${review.aptCode}`,review).then(success).catch(fail);

}
export { reviewList,addReview };