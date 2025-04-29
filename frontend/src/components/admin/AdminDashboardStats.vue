<template>
  <div class="flex gap-8 justify-center">
    <div class="bg-white p-6 rounded shadow w-48 text-center">
      <h3 class="text-lg font-bold">Crew Members</h3>
      <p class="text-3xl">{{ crewCount }}</p>
    </div>
    <div class="bg-white p-6 rounded shadow w-48 text-center">
      <h3 class="text-lg font-bold">Games</h3>
      <p class="text-3xl">{{ gameCount }}</p>
    </div>
  </div>
</template>

<script setup>
defineProps({
  crewCount: {
    type: Number,
    required: true,
  },
  gameCount: {
    type: Number,
    required: true,
  },
})
</script>
