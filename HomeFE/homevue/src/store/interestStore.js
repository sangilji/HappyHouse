import { interestList, add } from "@/api/interests";

const interestStore = {
  namespaced: true,
  state: {
    interestList: [],
  },
  getters: {},
  mutations: {
    SET_INTEREST_LIST(state, interestList) {
      state.interestList = interestList;
    },
  },
  actions: {
    async getInterests({ commit }, id) {
      await interestList(
        id,
        ({ data }) => {
          console.log("interest:");
          console.log(id);
          console.log(data);
          commit("SET_INTEREST_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async addInterest({ dispatch }, params) {
      await add(
        params,
        ({ data }) => {
          console.log(data.message);
          dispatch("getInterests",params.id);
        },
        (error) => {
          console.log(error);
        }
      )
    }
  },
};

export default interestStore;
