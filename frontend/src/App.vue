<template>
  Портфели:
  <ul>
    <li v-for="portfolio in portfolios" :key="portfolio.id">
      {{ portfolio.name }}
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
  async mounted() {
    const response = await fetch("/api/portfolios", {
      headers: {
        "Content-Type": "application/json",
      },
    });
    this.portfolios = await response.json();
  },
  computed: {},
};
</script>

<style></style>
