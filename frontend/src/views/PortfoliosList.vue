<template>
  <pulse-loader :loading="loading"></pulse-loader>
  <div v-if="!loading">
    Портфели
    <button
      v-if="!portfoliosIsEmpty"
      @click="$router.push({ name: 'portfolio' })"
    >
      +
    </button>
    <div v-else>
      <p>
        Для добавления нового портфеля нажмите на кнопку
        <button @click="$router.push({ name: 'portfolio' })">Добавить</button>
      </p>
    </div>
    <ul>
      <li v-for="portfolio in portfolios" :key="portfolio.id">
        {{ portfolio.id + " " + portfolio.name }}
        <button @click="deletePortfolio(portfolio.id)">Удалить</button>
        <button
          @click="
            $router.push({
              name: 'portfolio',
              params: { id: portfolio.id },
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
import { mapActions, mapGetters } from "vuex";

export default {
  async beforeMount() {
    await this.loadPortfolios();
    this.loading = false;
  },
  data: () => {
    return { loading: true };
  },
  methods: {
    ...mapActions("portfolio", ["loadPortfolios", "deletePortfolio"]),
  },
  computed: {
    ...mapGetters("portfolio", ["portfolios"]),
    portfoliosIsEmpty() {
      return this.portfolios.length === 0;
    },
  },
  components: { PulseLoader },
};
</script>

<style scoped></style>
