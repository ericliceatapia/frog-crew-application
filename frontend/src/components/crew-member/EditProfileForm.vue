<template>
  <form
    @submit.prevent="handleSubmit"
    class="space-y-2 bg-white p-6 rounded shadow"
  >
    <div class="flex gap-2">
      <label><b>First Name:</b></label>
      <input
        v-model="form.firstName"
        type="text"
        class="input border rounded px-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
    </div>
    <div class="flex gap-2">
      <label><b>Last Name:</b></label>
      <input
        v-model="form.lastName"
        type="text"
        class="input border rounded px-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
    </div>
    <div class="flex gap-2">
      <label><b>Phone Number:</b></label>
      <input
        v-model="form.phoneNumber"
        type="text"
        class="input border rounded px-2 focus:outline-none focus:ring-2 focus:ring-[#a072c6]"
        required
      />
    </div>

    <button
      type="submit"
      class="px-4 py-2 bg-[#4d1979] text-white rounded hover:bg-[#a072c6]"
    >
      Save Changes
    </button>
  </form>
</template>

<script setup>
import { ref } from 'vue'
import { updateUserProfile } from '@/apis/users'

const props = defineProps({
  user: Object,
})

const form = ref({ ...props.user })

async function handleSubmit() {
  try {
    await updateUserProfile(form.value.id, form.value)
    alert('Profile updated successfully!')
    window.location.reload()
  } catch (err) {
    console.error('Failed to update profile:', err)
    alert('Something went wrong. Please try again.')
  }
}
</script>
