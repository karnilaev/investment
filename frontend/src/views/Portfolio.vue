<template>
  <h1>{{ title }}</h1>
  <form class="portfolio" @submit.prevent="savePortfolio">
    <div class="form-control">
      <div>
        <label for="name">Наименование:</label>
        <input type="text" id="name" v-model.trim="portfolio.name" />
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
export default {
  data: () => ({ portfolio: { id: 0, name: "" }, errors: { name: null } }),
  beforeMount() {
    if (this.$route.params.id) {
      this.loadPortfolio();
    }
  },
  methods: {
    loadPortfolio() {
      fetch("/api/portfolio/" + this.$route.params.id).then((response) =>
        response.json().then((portfolio) => (this.portfolio = portfolio))
      );
    },
    async savePortfolio() {
      if (!this.isValid()) {
        return;
      }
      const response = await fetch("/api/portfolio/" + this.$route.params.id, {
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
};
</script>

<style></style>
