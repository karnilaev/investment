import { createStore, createLogger } from "vuex";
import portfolio from "./mudules/portfolio.module";
import currency from "./mudules/currency.module";
import auth from "./mudules/auth.module";

const plugins = [];

if (process.env.NODE_ENV === "development") {
  plugins.push(createLogger());
}

export default createStore({
  plugins,
  modules: {
    portfolio,
    currency,
    auth,
  },
});
