<template>
  <div class="bg-white p-4 rounded shadow space-y-3">
    <h3 class="text-xl font-bold mb-2">Assigned Crew</h3>
    <ul class="divide-y divide-gray-200">
      <li
        v-for="assignment in assignments"
        :key="assignment.userId"
        class="py-2"
      >
        <div class="font-medium">{{ assignment.position }}</div>
        <div class="text-sm text-gray-700">
          {{ assignment.fullName }} — Report: {{ assignment.reportTime || 'TBD' }}
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup>
defineProps({
  assignments: {
    type: Array,
    required: true
  }
})
</script>
