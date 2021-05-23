<template>
  <div id="app">
    <the-navbar></the-navbar>
    <router-view></router-view>
  </div>
</template>
<script>
import TheNavbar from "@/components/TheNavbar";

export default {
  components: { TheNavbar },
};
</script>
