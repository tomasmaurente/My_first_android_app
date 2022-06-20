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
    /* case a */    if (   reservation.startDateTimeInMillis <= reservationFromDataBase.endDate && reservation.startDateTimeInMillis > reservationFromDataBase.startDate
    /* case b */        || reservation.endDateTimeInMillis >= reservationFromDataBase.startDate && reservation.startDateTimeInMillis <= reservationFromDataBase.startDate
    /* case c */        || reservation.endDateTimeInMillis >= reservationFromDataBase.startDate && reservation.endDateTimeInMillis < reservationFromDataBase.endDate){
                        return AddPossibilities.Occupied
                    }
                }
                // Add reservation to database and service
                return when (addReservationRepository.addReservation(reservation)) {
                    is Result.Success -> AddPossibilities.Successful
                    is Result.Failure -> AddPossibilities.Fail
                }
            } else {
                return AddPossibilities.IncorrectDates
            }
        } else {
            return AddPossibilities.IncorrectParameters
        }
    }
}
