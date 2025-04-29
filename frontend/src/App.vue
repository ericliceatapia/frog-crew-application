<template>
  <RouterView />
  <div
    v-if="overlayVisible"
    class="fixed inset-0 z-50 bg-white flex items-center justify-center"
  >
    <p class="text-2xl font-semibold text-[#4d1979] animate-pulse">
      {{ overlayMessage }}
    </p>
  </div>
</template>

<script setup>
import { overlayVisible, overlayMessage } from '@/utils/overlay'
</script>
