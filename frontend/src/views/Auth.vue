<template>
  <form @submit.prevent="onSubmit">
    <h1>Войти в систему</h1>
    <div :class="['form - control', { invalid: emailError }]">
      <label for="email">E-mail</label>
      <input type="email" id="email" v-model="email" @blur="emailBlur" />
      <small v-if="emailError">{{ emailError }}</small>
    </div>
    <div :class="['form - control', { invalid: passwordError }]">
      <label for="password">Пароль</label>
      <input
        type="password"
        id="password"
        v-model="password"
        @blur="passwordBlur"
      />
      <small v-if="passwordError">{{ passwordError }}</small>
    </div>
    <button class="btn-primary" type="submit" :disabled="isSubmitting">
      Войти в систему
    </button>
  </form>
</template>

<script>
import { useLoginForm } from "@/use/login-form";

export default {
  setup() {
    return useLoginForm();
  },
};
</script>

<style scoped></style>
