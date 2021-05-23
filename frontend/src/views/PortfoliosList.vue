<template>
  <pulse-loader :loading="loading"></pulse-loader>
  <div v-if="!loading">
    Портфели
    <button v-if="!portfoliosIsEmpty" @click="$router.push('/portfolio')">
      +
    </button>
    <div v-else>
      <p>
        Для добавления нового портфеля нажмите на кнопку
        <button @click="$router.push('/portfolio')">Добавить</button>
      </p>
    </div>
    <ul>
      <li v-for="portfolio in portfolios" :key="portfolio.id">
        {{ portfolio.id + " " + portfolio.name }}
        <button @click="deletePortfolio(portfolio.id)">Удалить</button>
        <button
          @click="
            $router.push({
              path: '/portfolio/' + portfolio.id,
            })
          "
        >
          Редактировать
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
import PulseLoader from "vue-spinner/src/PulseLoader.vue";

export default {
  data() {
    return {
      loading: true,
      portfolios: [],
    };
  },
  beforeMount() {
    this.loadPortfolios();
  },
  methods: {
    loadPortfolios() {
      fetch("/api/v1/portfolio/list").then((response) =>
        response.json().then((portfolio) => {
          this.portfolios = portfolio;
          this.loading = false;
        })
      );
    },
    async deletePortfolio(id) {
      const response = await fetch("/api/v1/portfolio/" + id, {
        method: "DELETE",
      });
      if (response.ok) {
        const index = this.portfolios.findIndex(
          (portfolio) => portfolio.id === id
        );
        if (index >= 0) {
          this.portfolios.splice(index, 1);
        }
      }
    },
  },
  computed: {
    portfoliosIsEmpty() {
      return this.portfolios.length === 0;
    },
  },
  components: { PulseLoader },
};
</script>

<style></style>
