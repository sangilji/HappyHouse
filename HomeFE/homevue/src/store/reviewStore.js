import { reviewList,addReview } from "@/api/review";
const dealInfoStore = {
  namespaced: true,
  state: {
    reviewList:[],
    
  }
  , actions: {
    async getReview({ commit }, aptCode) {
      console.log("여기는 들어왔음");
      await reviewList(
        aptCode,
        ({ data }) => {
          commit("SET_REVIEW_LIST", data);
          console.log("review");
          console.log(data);
        }
        ,
        (error) => {
          console.log(error);
        }
      );
    },
    async newReview(store,review){
      await addReview(
        review,
        ({data})=>{
          console.log(data);
        },
        (error)=>{
          console.log(error);
        }
      )
    }
  }
  , mutations: {
    SET_REVIEW_LIST(state, reviewList) {
      reviewList.forEach(element => {
        element.stars = parseFloat(element.stars);
      });
      console.log(reviewList);
      state.reviewList = reviewList;
    }
  }
}

export default dealInfoStore;