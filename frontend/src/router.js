import { createRouter, createWebHistory } from "vue-router";
import store from "@/store";

const routes = [
  {
    name: "index",
    path: "/",
    component: () => import("@/views/PortfoliosList"),
    meta: { layout: "main", auth: true },
  },
  {
    name: "portfolio",
    path: "/portfolio/:id?",
    component: () => import("@/views/Portfolio"),
    meta: { layout: "main", auth: true },
  },
  {
    name: "auth",
    path: "/auth",
    component: () => import("@/views/Auth"),
    meta: { layout: "auth", auth: false },
  },
];

const router = createRouter({
  routes,
  history: createWebHistory(),
});

router.beforeEach((to, from, next) => {
  const requireAuth = to.meta.auth;
  if (requireAuth && !store.getters["auth/isAuthenticated"]) {
    next({ name: "auth", params: { message: "auth" } });
  } else {
    next();
  }
});

export default router;
