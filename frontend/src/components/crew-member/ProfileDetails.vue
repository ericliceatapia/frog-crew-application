<template>
  <div v-if="user" class="bg-white space-y-2 p-6 rounded shadow">
    <p><b>Name:</b> {{ user.firstName }} {{ user.lastName }}</p>
    <p><b>Email:</b> {{ user.email }}</p>
    <p><b>Phone:</b> {{ user.phoneNumber }}</p>
    <p><b>Role:</b> {{ user.role }}</p>
    <p><b>Positions:</b> {{ user.positions?.join(', ') || 'N/A' }}</p>
  </div>
  <div v-else class="text-center text-gray-500 py-8">
    Loading your profile...
  </div>
</template>

<script setup>
defineProps({ user: Object })
</script>
