<template>
  <div class="bg-white p-6 rounded shadow">
    <h2 class="text-xl font-bold mb-2">Profile Summary</h2>
    <p v-if="user">
      <b>{{ user.firstName }} {{ user.lastName }}</b> <br />
      <span class="text-gray-600">{{ user.email }}</span> <br />
      <span class="text-gray-500 capitalize">{{
        user.role.toLowerCase()
      }}</span>
    </p>
  </div>
</template>

<script setup>
defineProps({ user: Object })
</script>
