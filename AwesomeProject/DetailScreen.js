import { FlatList, Text, View } from "react-native";
import React from "react";

const DetailScreen = (props) => {
    console.log(props.route.params.colors);
    return (
        <View>
            <FlatList
                data={props.route.params.colors}
                renderItem={({ item }) => (
                    <View
                        style={{
                            height: 50,
                            width: 350,
                            backgroundColor: item.hexCode,
                        }}
                    >
                        <Text>{item.colorName}</Text>
                    </View>
                )}
            />
        </View>
    );
};

export default DetailScreen;
