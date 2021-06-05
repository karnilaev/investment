export default {
  namespaced: true,
  name: "currency",
  state() {
    return {
      currencies: [],
    };
  },
  getters: {
    currencies(state) {
      return state.currencies;
    },
  },
  mutations: {
    loadCurrencies(state, currencies) {
      state.currencies = currencies;
    },
  },
  actions: {
    async loadCurrencies({ commit }) {
      const response = await fetch("/api/v1/currency/list");
      const currencies = await response.json();
      commit("loadCurrencies", currencies);
    },
  },
};
