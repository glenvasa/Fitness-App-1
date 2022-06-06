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
const moveMealModal = (event) => {
event.preventDefault()
    const mealDateButtons = document.querySelectorAll('.mealDate')
    const mealModal = document.getElementById('mealModal')
    const mealModalContent = document.getElementById("mealModalContent")
    const closeModalButton = document.getElementById('closeMealModal')

mealModal.style.top = 0;

//    mealModal.classList.remove('invisible')

    closeModalButton.onclick = function() {
//        mealModal.classList.add('invisible')
//        mealModalContent.innerText = ""
    }

    // only want window onclick to function IF model is open; otherwise it will prevent other page button onclick fns
    window.onclick = function(e) {
          if (e.target == mealModal && !mealModal.classList.contains('invisible')) {

//            mealModal.classList.add('invisible')
//            mealModalContent.innerText = ""
            window.onclick = null;
          }
    }

//   console.log(event.target.innerText)

//    fetchMealData(event.target.innerText)


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

fetchWorkoutData(event.target.innerText)

}


//if(window.location.pathname == "/profile") {
//window.addEventListener("load", () => {
//    const mealModal = document.getElementById('mealModal')
//    mealModal.classList.add('invisible')
//})




const fetchMealData = (event) => {

window.location.reload()
   const date = event.target.innerText
//   console.log(date)
   fetch(`/profile/meals/${date}`)

//      .catch(err => {console.log(err)})
//mealModal.classList.add('invisible')
//moveMealModal(event)

}

const fetchWorkoutData = (date) => {
   console.log(date)
   fetch(`/profile/workouts/${date}`)
      .then(res => { console.log(res)})
      .catch(err => {console.log(err)})
}



const modalUp = (event) => {
//  event.preventDefault()

const mealModal = document.getElementById('mealModal')
  mealModal.style.top = 0;
  console.log('timeout')


}