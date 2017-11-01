import Ember from 'ember';
import Reservation from 'ember-restaurants/models/reservation';

export default Ember.Controller.extend({
  session: Ember.inject.service(),
  reservationService: Ember.inject.service(),
  hasAvailable: false,
  availableTimes: [],
  hasError: false,
  error: "",
  numOfTables: 0,
  reservation: Reservation.create({
    persons: "1 people",
    date: new Date(),
    hour: "11:00 AM"
  }),
  clear: function() {
        this.setProperties({
            availableTimes: [],
            hasAvailable: false,
            hasError: false,
            error: "",
            numOfTables: 0
        });
    }.observes('model'),
  actions: {
    findTable: function() {
      var restaurantId = this.get('model.id');
      var reservation = this.get('reservation');
      this.get('reservationService').checkReservationAvailability(restaurantId, reservation).then(function(result) {
        this.set('numOfTables', result.tablesLeft);
        if (result.tablesLeft > 0) {

          this.set('availableTimes', result.bestTime);
          this.set('hasError', false);
          this.set('hasAvailable',true);
        }
        else{
          this.set('error',"No tables available for specified time.");
          this.set('hasError',true);
        }
      }.bind(this));
    },

    reserveTable: function(time){
        this.set('reservationTime',time);
        this.transitionToRoute('reservation');
    }
  }
});
