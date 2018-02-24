import Ember from 'ember';

export default Ember.Service.extend({

  ajaxService: Ember.inject.service(),
  checkReservationAvailability: function(id, reservation) {
    return this.get('ajaxService').request('/checkReservationAvailability', {
      method: 'POST',
      data: JSON.stringify({
        idRestaurant: id,
        people: reservation.people,
        hour: reservation.hour,
        date: reservation.date
      })

    });
  },

  makeReservation: function(_idRestaurant, _persons,
                                    _reservationDate, _reservationHour, _idUser) {
    console.log(_reservationDate);
    return this.get('ajaxService').request('/makeReservation', {
      method: 'POST',
      data: JSON.stringify({
        idRestaurant: _idRestaurant,
        persons: _persons,
        reservationDate: _reservationDate,
        reservationHour: _reservationHour,
        idUser: _idUser
      })
    });
  }


});
