<template>
  <div class="text-center py-20">
    <div class="text-2xl font-bold text-[#4d1979] animate-pulse">
      {{ message }}
    </div>
  </div>
</template>

<script setup>
defineProps({
  message: {
    type: String,
    default: 'Loading...',
  },
})
</script>
