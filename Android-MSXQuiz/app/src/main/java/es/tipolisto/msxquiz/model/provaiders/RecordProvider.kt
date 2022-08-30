package es.tipolisto.msxquiz.model.provaiders

import es.tipolisto.msxquiz.model.database.RecordEntity


class RecordProvider {
    companion object {
        private var recordList= emptyList<RecordEntity>()

        fun getRecordList():List<RecordEntity>{
            return recordList.toMutableList()
        }

        fun setRecordList(recordListEntry:List<RecordEntity>){
            recordList=recordListEntry
        }

        fun getStaticListRecords():List<RecordEntity>{
            val list= listOf<RecordEntity>(
                RecordEntity(
                    id=0,
                    name="Ganodi",
                    date="21/7/2021",
                    score=1560
                ),
                RecordEntity(
                    id=0,
                    name="Sly Moore",
                    date="4/4/2022",
                    score=14570
                ),
                RecordEntity(
                    id=0,
                    name="Lott Dod",
                    date="1/10/2022",
                    score=780
                ),
                RecordEntity(
                    id=0,
                    name="Lama Su",
                    date="14/3/2022",
                    score=5800
                ),
                RecordEntity(
                    id=0,
                    name="Kylo Ren",
                    date="10/8/2022",
                    score=2350
                ))
            return list
        }

    }

}