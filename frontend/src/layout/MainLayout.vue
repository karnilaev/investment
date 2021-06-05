<template>
  <the-navbar></the-navbar>
  <router-view />
</template>

<script>
import TheNavbar from "@/components/TheNavbar";

export default {
  components: { TheNavbar },
};
</script>

<style scoped></style>
