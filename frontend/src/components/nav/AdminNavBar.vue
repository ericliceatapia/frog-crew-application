<template>
  <nav
    class="flex items-center justify-between p-4 bg-[#4d1979] text-white font-medium"
  >
    <!-- Left: Site name -->
    <div class="text-xl font-bold">Frog Crew</div>

    <!-- Right: Navigation links -->
    <div class="flex gap-4">
      <RouterLink to="/admin">Home</RouterLink>
      <RouterLink to="/admin/crew-list">Roster</RouterLink>
      <RouterLink to="/admin/schedule/create">Create Schedule</RouterLink>
      <RouterLink to="/login?loggedOut=true" @click.prevent="handleLogout"
        >Log Out</RouterLink
      >
    </div>
  </nav>
</template>

<script setup>
import { logout } from '@/apis/auth'
import { showOverlay, hideOverlay } from '@/utils/overlay'

async function handleLogout() {
  try {
    await logout()
    showOverlay('Logging out...')
  } catch (err) {
    console.error('Logout error:', err)
  } finally {
    setTimeout(() => {
      hideOverlay()
    }, 2000)
  }
}
</script>
