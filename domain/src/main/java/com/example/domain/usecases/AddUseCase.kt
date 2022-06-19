package com.example.domain.usecases

import com.example.domain.entities.Reservation
import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.Result
import com.example.domain.repositories.AddRepository
import com.example.domain.utils.AddPossibilities

class AddUseCase(var addReservationRepository: AddRepository) {

    suspend operator fun invoke(reservation: Reservation,
                                reservationList: ReservationListModel? ): AddPossibilities {

        if(reservation.parkingLot > -1){
            if (reservation.startDateTimeInMillis < reservation.endDateTimeInMillis){
                reservationList?.reservationList?.forEach { reservationFromDataBase ->
                    if (   reservationFromDataBase.endDate > reservation.startDateTimeInMillis
                        || reservationFromDataBase.startDate > reservation.endDateTimeInMillis){
                        return AddPossibilities.Occupied
                    }
                }
                // Add reservation to database and service
                var newAddition = addReservationRepository.addReservation(reservation)
                when (newAddition) {
                    is Result.Success -> return AddPossibilities.Successful
                    is Result.Failure -> return AddPossibilities.Fail
                }
            } else {
                return AddPossibilities.IncorrectDates
            }
        } else {
            return AddPossibilities.IncorrectParameters
        }
    }
}
