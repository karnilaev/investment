<template>
  <nav class="navbar">
    <ul>
      <li><router-link to="/">Главная</router-link></li>
      <li>
        <a href="#" @click.prevent="logout">Выход</a>
      </li>
    </ul>
  </nav>
</template>

<script>
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
};
</script>

<style scoped>
a.router-link-active {
  font-weight: bold;
}
</style>
