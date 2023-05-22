import {
  sidoList,
  gugunList,
  dongList,
  getHouseList,
  getHouseList2,
  houseDeal,
} from "@/api/item/item";

const dealInfoStore = {
  namespaced: true,
  state: {
    sidos: [],
    guguns: [{ value: null, text: "구 선택" }],
    dongs: [{ value: null, text: "동 선택" }],
    houseList: [],
    fromMainKeyword: "",
    currentIndex: null,
    houseDealInfo: [],
  },
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시 선택" }];
    },
    SET_SIDOS(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "구 선택" }];
    },
    SET_GUGUNS(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "동 선택" }];
    },
    SET_DONGS(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_HOUSE_LIST(state, houseList) {
      state.houseList = houseList;
    },
    SET_KEYWORD(state, keyword) {
      state.fromMainKeyword = keyword;
    },
    CLEAR_KEYWORD(state, keyword) {
      state.fromMainKeyword = keyword;
    },
    SET_CURRENT_INDEX(state, index) {
      state.currentIndex = index;
    },
    SET_HOUSE_DEAL_INFO(state, houseDeal) {
      state.houseDealInfo = houseDeal;
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDOS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = { sidoCode: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUNS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, code) => {
      const params = {
        sidoCode: code.sidoCode,
        gugunCode: code.gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONGS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getHouseListByDong({ commit }, dong) {
      const params = {
        dongCode: dong,
      };

      await getHouseList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
          if(data.length>0){
            commit("SET_CURRENT_INDEX", 0);
          } else {
            commit("SET_CURRENT_INDEX", null);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getDealInfo({ commit, state }) {
      await houseDeal(
        state.houseList[state.currentIndex].aptCode,
        ({ data }) => {
          commit("SET_HOUSE_DEAL_INFO", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getHouseListByKeyword({ commit }, dong) {
      await getHouseList2(
        dong,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default dealInfoStore;
