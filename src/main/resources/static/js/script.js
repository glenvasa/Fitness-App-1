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

// opens Meal Modal when mealDate-button clicked
const openMealModal = (event) => {
    const mealDateButtons = document.querySelectorAll('.mealDate')
    const mealModal = document.getElementById('mealModal')
    const mealModalContent = document.getElementById("mealModalContent")
    const closeModalButton = document.getElementById('closeMealModal')

    mealModal.classList.remove('hidden')

    closeModalButton.onclick = function() {
        mealModal.classList.add('hidden')
//        mealModalContent.innerText = ""
    }

    // only want window onclick to function IF model is open; otherwise it will prevent other page button onclick fns
    window.onclick = function(e) {
          if (e.target == mealModal && !mealModal.classList.contains('hidden')) {
            mealModal.classList.add('hidden')
//            mealModalContent.innerText = ""
            window.onclick = null;
          }
    }

//   console.log(event.target.innerText)

    fetchMealData(event.target.innerText)


}

// opens Workout Modal when workoutDate-button clicked
const openWorkoutModal = () => {
    const workoutDateButtons = document.querySelectorAll('.workoutDate')
    const workoutModal = document.getElementById('workoutModal')
    const closeModalButton = document.getElementById('closeWorkoutModal')

    workoutModal.classList.remove('hidden')

    closeModalButton.onclick = () => workoutModal.classList.add('hidden')

    // only want window onclick to function IF model is open; otherwise it will prevent other page button onclick fns
    window.onclick = function(e) {
          if (e.target == workoutModal && !workoutModal.classList.contains('hidden')) {
            workoutModal.classList.add('hidden')
            window.onclick = null;
          }
    }


}

const fetchMealData = (date) => {
   console.log(date)
   fetch(`/profile/meals/${date}`)
      .then(res => { console.log(res)})
      .catch(err => {console.log(err)})
}