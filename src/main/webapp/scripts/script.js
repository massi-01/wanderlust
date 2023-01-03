
// Add TravelModal

function setupAddTravelModal(){
  const addTravelModal = document.getElementById("myModal");
  const travelModalButton = document.getElementById("openModal");
  const closeTravelModal = addTravelModal.querySelector(".close");

  travelModalButton.onclick = () => addTravelModal.style.display = "block";
  closeTravelModal.onclick = () => addTravelModal.style.display = "none";
}

function setupEditTravelModals(){

  const openModalButtons = document.querySelectorAll("[id^=myBtn-]");

  openModalButtons.forEach(openButton => {
    const target = openButton.dataset.targetmodal;
    const modal = document.getElementById(target);
    const closeButton = modal.querySelector(".close");

    openButton.onclick = () => modal.style.display = "block";
    closeButton.onclick = () => modal.style.display = "none";
  });

}

window.onload = () => {
  setupAddTravelModal();
  setupEditTravelModals();
}
