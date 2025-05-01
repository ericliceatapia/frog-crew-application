import { ref } from 'vue'

export const overlayVisible = ref(false)
export const overlayMessage = ref('')

export function showOverlay(message = 'Loading...') {
  overlayMessage.value = message
  overlayVisible.value = true
}

export function hideOverlay() {
  overlayVisible.value = false
  overlayMessage.value = ''
}
