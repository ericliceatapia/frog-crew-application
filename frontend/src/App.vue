<template>
  <RouterView />
  
  <Transition
    name="fade"
    enter-active-class="transition-opacity duration-0"
    leave-active-class="transition-opacity duration-750"
    enter-from-class="opacity-0"
    enter-to-class="opacity-100"
    leave-from-class="opacity-100"
    leave-to-class="opacity-0"
  >
    <div
      v-if="overlayVisible"
      class="fixed inset-0 z-50 bg-white flex items-center justify-center"
    >
      <p class="text-2xl font-semibold text-[#4d1979] animate-pulse">
        {{ overlayMessage }}
      </p>
    </div>
  </Transition>
</template>

<script setup>
import { overlayVisible, overlayMessage } from '@/utils/overlay'
</script>
