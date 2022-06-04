// only want "load" event listener to be added and call addServingsCalories if /servings route
if(window.location.pathname === "/servings") {
window.addEventListener("load", () => {
    console.log(window.location.pathname)
    addServingsCalories();
})

}

// Add up Calories for each Serving on Meal Card and set = Total Meal Calories
const addServingsCalories = () => {
    const servingsCals = document.querySelectorAll('#totalCals')
    console.log(servingsCals)
    const mealCals = document.getElementById('mealCals')
    let totalMealCals = 0
    servingsCals.forEach(s => totalMealCals += Number(s.innerText));
   console.log(totalMealCals)
   mealCals.value = totalMealCals
}

// opens Meal Modal when date-button clicked
const openMealModal = () => {
    const mealDateButtons = document.querySelectorAll('.mealDate')
    const mealModal = document.getElementById('mealModal')
    const closeModalButton = document.getElementById('closeModal')

    mealModal.classList.remove('hidden')

    closeModalButton.onclick = () => mealModal.classList.add('hidden')

    // only want window onclick to function IF model is open; otherwise it will prevent other page button onclick fns
    window.onclick = function(e) {
          if (e.target == mealModal && !mealModal.classList.contains('hidden')) {
            mealModal.classList.add('hidden')
            window.onclick = null;
          }
    }


}