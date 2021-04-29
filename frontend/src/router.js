import { createRouter, createWebHistory } from "vue-router";
import Portfolios from "./views/PortfoliosList";
import Portfolio from "@/views/Portfolio";

export default createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/portfolio/list", component: Portfolios, alias: "/" },
    { path: "/portfolio/:id?", component: Portfolio },
  ],
  linkActiveClass: "active-link",
  linkExactActiveClass: "active-exact-link",
});
