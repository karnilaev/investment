import { useField, useForm } from "vee-validate";
import * as yup from "yup";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export function useLoginForm() {
  const store = useStore();
  const router = useRouter();
  const { handleSubmit, isSubmitting } = useForm();

  const {
    value: email,
    errorMessage: emailError,
    handleBlur: emailBlur,
  } = useField(
    "email",
    yup
      .string()
      .trim()
      .required("E-mail обязателен для заполнения")
      .email("Введите валидный E-mail")
  );

  const PASSWORD_MIN_LENGTH = 6;
  const {
    value: password,
    errorMessage: passwordError,
    handleBlur: passwordBlur,
  } = useField(
    "password",
    yup
      .string()
      .trim()
      .required("Пароль обязателен для заполнения")
      .min(
        PASSWORD_MIN_LENGTH,
        `Минимальная длина пароля: ${PASSWORD_MIN_LENGTH} символов`
      )
  );

  const onSubmit = handleSubmit(async (values) => {
    await store.dispatch("auth/login", values);
    await router.push({ name: "index" });
  });

  return {
    email,
    password,
    emailError,
    passwordError,
    emailBlur,
    passwordBlur,
    onSubmit,
    isSubmitting,
  };
}
