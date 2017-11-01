import Ember from 'ember';

export default Ember.Controller.extend({
  errorExists: false,
  error: "",
  reservationService: Ember.inject.service(),
  session: Ember.inject.service(),

  actions: {
    makeReservation: function(){
      var persons = this.get('reservation.people');
      var time = this.get('reservationTime');
      var idRestaurant = this.get('restaurant.id');
      var date = this.get('reservation.date');
      var user_id = this.get('session.data.authenticated.id');
      this.get('reservationService').makeReservation(idRestaurant, persons, date, time, user_id).then(function() {
                this.set('errorExists', false);
                this.set('error', "");
                this.transitionToRoute('index');
            }.bind(this), function(data) {
                this.set('errorExists', true);
                this.set('error', data.responseText);
            }.bind(this));
    }


  }
});
