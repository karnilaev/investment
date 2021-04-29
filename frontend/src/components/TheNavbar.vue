<template>
  <header class="navbar">
    <ul>
      <li><router-link to="/">Главная</router-link></li>
      <li><router-link to="/portfolio">Новый портфель</router-link></li>
    </ul>
  </header>
</template>

<script>
export default {};
</script>

<style scoped>
a.active-link {
  font-weight: bold;
}
</style>
