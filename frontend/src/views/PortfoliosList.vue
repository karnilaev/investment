<template>
  <div class="p-flex-column">
    <div>
      <Button
        label="Добавить сделку"
        icon="pi pi-plus"
        class="p-button-text"
        @click="$router.push({ name: 'deal' })"
      />
      <Button
        label="Добавить портфель"
        icon="pi pi-plus"
        class="p-button-text"
        @click="$router.push({ name: 'portfolio' })"
      />
    </div>
    <div class="card">
      <DataTable :value="portfolios" :loading="loading" data-key="id">
        <Column field="name" header="Наименование"></Column>
        <Column field="startDate" header="Дата постановки"></Column>
        <Column field="targetDate" header="Дата достижения"></Column>
        <Column field="target" header="Сумма"></Column>
        <Column header="Валюта"
          ><template #body="slotProps">
            {{ currencyName(slotProps.data.currencyId) }}
          </template></Column
        >
        <Column header="Действия">
          <template #body="slotProps">
            <Button
              type="button"
              icon="pi pi-pencil"
              class="p-button"
              style="margin-right: 0.5em"
              @click="
                $router.push({
                  name: 'portfolio',
                  params: { id: slotProps.data.id },
                })
              "
            ></Button>
            <Button
              type="button"
              icon="pi pi-times"
              class="p-button-danger"
              @click="deletePortfolio(slotProps.data.id)"
            ></Button>
          </template>
        </Column>
      </DataTable>
    </div>
  </div>
</template>

<script>
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Button from "primevue/button";
import { mapActions, mapGetters } from "vuex";

export default {
  async beforeMount() {
    await this.loadCurrencies();
    await this.loadPortfolios();
    this.loading = false;
  },
  data: () => {
    return { loading: true };
  },
  methods: {
    ...mapActions("portfolio", ["loadPortfolios", "deletePortfolio"]),
    ...mapActions("currency", ["loadCurrencies"]),
    currencyName(currencyId) {
      return this.currencies.find((currency) => currency.id === currencyId)
        .name;
    },
  },
  computed: {
    ...mapGetters("portfolio", ["portfolios"]),
    ...mapGetters("currency", ["currencies"]),
    portfoliosIsEmpty() {
      return this.portfolios.length === 0;
    },
  },
  components: { DataTable, Column, Button },
};
</script>

<style scoped>
.p-button {
  margin-right: 0.5rem;
}
</style>
