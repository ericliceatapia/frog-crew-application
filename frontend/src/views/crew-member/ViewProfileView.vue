<!-- Route: /crew-member/profile -->

<template>
  <div class="max-w-4xl mx-auto py-8 space-y-8">
    <LoadingSpinner v-if="loading" message="Loading profile..."/>

    <div v-else class="space-y-8">
      <h1 class="text-3xl font-bold mb-2">My Profile</h1>

      <div class="flex gap-4 justify-center m-4">
        <button
          @click="isEditing = false"
          :class="{ 'font-bold underline': !isEditing }"
        >
          View
        </button>
        <button
          @click="isEditing = true"
          :class="{ 'font-bold underline': isEditing }"
        >
          Edit
        </button>
      </div>

      <ProfileDetails v-if="!isEditing" :user="user" />
      <EditProfileForm v-else :user="user" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getUserById } from '@/apis/users'

import LoadingSpinner from '@/components/common/LoadingSpinner.vue'
import ProfileDetails from '@/components/crew-member/ProfileDetails.vue'
import EditProfileForm from '@/components/crew-member/EditProfileForm.vue'

const user = ref([])
const isEditing = ref(false)
const loading = ref(true)

onMounted(async () => {
  try {
    const userId = localStorage.getItem('userId')
    if (userId) {
      user.value = await getUserById(userId)
    }
  } catch (err) {
    console.error('Error loading user profile:', err)
  } finally {
    window.setInterval(() => {
      loading.value = false
    }, 500)
  }
})
</script>
