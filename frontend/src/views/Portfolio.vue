<template>
  <div class="p-d-flex">
    <div class="card">
      <h3>{{ title }}</h3>
      <pulse-loader :loading="loading"></pulse-loader>
      <form class="card" @submit.prevent="savePortfolio" v-if="!loading">
        <div class="p-field p-col-12 p-md-6">
          <label for="name">Наименование</label>
          <InputText id="name" type="text" v-model.trim="portfolio.name" />
        </div>
        <div class="p-field p-col-12 p-md-6">
          <label for="target">Цель</label>
          <InputNumber
            id="target"
            v-model="portfolio.target"
            :minFractionDigits="2"
            :maxFractionDigits="2"
            mode="decimal"
          />
          <Dropdown
            v-model="portfolio.currencyId"
            :options="currencies"
            optionLabel="name"
            optionValue="id"
            placeholder="Валюта"
          />
        </div>
        <div class="p-field p-col-12 p-md-6">
          <label for="start_date">Дата постановки цели</label>
          <Calendar
            id="start_date"
            v-model="portfolio.startDate"
            selectionMode="single"
            dateFormat="yy-mm-dd"
            :show-icon="true"
          ></Calendar>
        </div>
        <div>
          <Button type="submit" label="Сохранить" icon="pi pi-check" />
          <Button
            class="p-button-text"
            label="Отмена"
            icon="pi pi-times"
            @click="$router.back()"
          />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import PulseLoader from "vue-spinner/src/PulseLoader.vue";
import InputNumber from "primevue/inputnumber";
import InputText from "primevue/inputtext";
import Button from "primevue/button";
import Calendar from "primevue/calendar";
import Dropdown from "primevue/dropdown";
import { mapActions, mapGetters } from "vuex";

export default {
  data: () => ({
    loading: true,
    portfolio: {
      id: 0,
      name: "",
      startDate: new Date().toISOString().slice(0, 10),
      targetDate: "0000-00-00",
      target: 0,
      currencyId: 2,
    },
    errors: { name: null },
  }),
  async beforeMount() {
    await this.loadCurrencies();
    if (this.$route.params.id) {
      const foundPortfolio = await this.portfolioById(this.$route.params.id);
      if (foundPortfolio) {
        this.portfolio = foundPortfolio;
        this.loading = false;
      }
    } else {
      this.loading = false;
    }
  },
  methods: {
    ...mapActions("portfolio", ["portfolioById"]),
    ...mapActions("currency", ["loadCurrencies"]),
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
        await this.$router.push({ name: "index" });
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
    ...mapGetters("currency", ["currencies"]),
    title() {
      if (this.$route.params.id) {
        return "Редактирование портфеля";
      } else {
        return "Создание нового портфеля";
      }
    },
  },
  components: {
    PulseLoader,
    InputNumber,
    InputText,
    Calendar,
    Button,
    Dropdown,
  },
};
</script>

<style></style>
