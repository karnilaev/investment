<template>
  <Menubar :model="items" />
</template>

<script>
import Menubar from "primevue/menubar";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default {
  setup() {
    const router = useRouter();
    const store = useStore();
    return {
      logout: () => {
        store.commit("auth/logout");
        router.push({ name: "auth" });
      },
    };
  },
  data() {
    return {
      items: [
        {
          label: "Главная",
          icon: "pi pi-fw pi-home",
          to: { name: "index" },
        },
        {
          label: "Выход",
          icon: "pi pi-fw pi-power-off",
          to: { name: "auth" },
        },
      ],
    };
  },
  components: { Menubar },
};
</script>

<style scoped>
a.router-link-active {
  font-weight: bold;
}
</style>
