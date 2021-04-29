<template>
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
      {{ portfolio.name }}
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
</template>

<script>
export default {
  data() {
    return {
      portfolios: [],
    };
  },
  beforeMount() {
    this.loadPortfolios();
  },
  methods: {
    loadPortfolios() {
      fetch("/api/portfolio/list").then((response) =>
        response.json().then((portfolio) => (this.portfolios = portfolio))
      );
    },
    async deletePortfolio(id) {
      const response = await fetch("/api/portfolio/" + id, {
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
};
</script>

<style></style>
