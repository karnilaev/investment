<template>
  <h1>{{ title }}</h1>
  <pulse-loader :loading="loading"></pulse-loader>
  <form class="portfolio" @submit.prevent="savePortfolio" v-if="!loading">
    <div class="form-control">
      <div>
        <label for="name">Наименование:</label>
        <input type="text" id="name" v-model.trim="portfolio.name" />
      </div>
      <div>
        <label for="target">Сумма:</label>
        <input type="number" id="target" v-model.number="portfolio.target" />
        <select id="currency" v-model="portfolio.currencyId">
          <option
            v-for="currency in currencies"
            :value="currency.id"
            :key="currency.id"
          >
            {{ currency.name }}
          </option>
        </select>
      </div>
      <div>
        <label for="target_date">Планируемая дата достижения цели:</label>
        <input type="date" id="target_date" v-model="portfolio.targetDate" />
      </div>
      <small v-if="errors.name">{{ errors.name }}</small>
    </div>
    <div>
      <button type="submit">Сохранить</button>
      <button type="reset" @click="$router.back()">Отмена</button>
    </div>
  </form>
</template>

<script>
import PulseLoader from "vue-spinner/src/PulseLoader.vue";

export default {
  data: () => ({
    loading: true,
    portfolio: {
      id: 0,
      name: "",
      targetDate: "0000-00-00",
      target: 0,
      currencyId: 2,
    },
    currencies: [
      { id: 1, name: "RUB" },
      { id: 2, name: "USD" },
      { id: 3, name: "EUR" },
    ],
    errors: { name: null },
  }),
  beforeMount() {
    if (this.$route.params.id) {
      this.loadPortfolio();
    } else {
      this.loading = false;
    }
  },
  methods: {
    loadPortfolio() {
      fetch("/api/v1/portfolio/" + this.$route.params.id).then((response) =>
        response.json().then((portfolio) => {
          this.portfolio = portfolio;
          this.loading = false;
        })
      );
    },
    async savePortfolio() {
      if (!this.isValid()) {
        return;
      }
      const response = await fetch("/api/v1/portfolio", {
        method: "POST",
        headers: {
          "content-type": "application/json",
        },
        body: JSON.stringify(this.portfolio),
      });
      if (response.ok) {
        await this.$router.push("/portfolio/list");
      }
    },
    isValid() {
      this.errors.name = this.portfolio.name
        ? null
        : "Наименование обязательно для заполнения";

      return this.errors.name === null;
    },
  },
  computed: {
    title() {
      if (this.$route.params.id) {
        return "Редактирование портфеля";
      } else {
        return "Создание нового портфеля";
      }
    },
  },
  components: { PulseLoader },
};
</script>

<style></style>
