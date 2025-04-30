<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <div class="bg-white p-6 rounded shadow-md space-y-4 w-96">
      <h2 class="text-xl font-bold text-center text-[#4d1979]">Confirm Deletion</h2>
      <p class="text-center">Are you sure you want to delete {{ user.firstName }} {{ user.lastName }}?</p>
      <div class="flex justify-center gap-4 mt-4">
        <button @click="$emit('cancel')" class="px-4 py-2 bg-gray-400 hover:bg-gray-500 text-white rounded">
          Cancel
        </button>
        <button @click="$emit('confirm')" class="px-4 py-2 bg-red-500 hover:bg-red-600 text-white rounded">
          Delete
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  user: Object
})
</script>