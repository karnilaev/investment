export default {
  namespaced: true,
  name: "portfolio",
  state() {
    return {
      list: [],
    };
  },
  getters: {
    portfolios(state) {
      return state.list;
    },
  },
  mutations: {
    setPortfolios(state, portfolios) {
      state.list = portfolios;
    },
    deletePortfolio(state, id) {
      const index = state.list.findIndex((portfolio) => portfolio.id === id);
      if (index >= 0) {
        state.list.splice(index, 1);
      }
    },
  },
  actions: {
    async loadPortfolios({ commit }) {
      const response = await fetch("/api/v1/portfolio/list");
      const list = await response.json();
      commit("setPortfolios", list);
    },
    async portfolioById(_, id) {
      const response = await fetch("/api/v1/portfolio/" + id);
      return await response.json();
    },
    async deletePortfolio({ commit }, id) {
      const response = await fetch("/api/v1/portfolio/" + id, {
        method: "DELETE",
      });
      if (response.ok) {
        commit("deletePortfolio", id);
      }
    },
  },
};
