import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "@/store";
import "primeflex/primeflex.css";
import "primevue/resources/themes/fluent-light/theme.css";
import "primevue/resources/primevue.min.css";
import "primeicons/primeicons.css";
import "@/main.css";
import PrimeVue from "primevue/config";

const app = createApp(App).use(router).use(store);
app.use(PrimeVue, {
  zIndex: {
    modal: 1100, //dialog, sidebar
    overlay: 1000, //dropdown, overlaypanel
    menu: 1000, //overlay menus
    tooltip: 1100, //tooltip
    inputStyle: "filled",
  },
});
app.mount("#app");
