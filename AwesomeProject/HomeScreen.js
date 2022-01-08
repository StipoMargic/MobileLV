import { useNavigation } from "@react-navigation/native";
import React, { useState, useEffect } from "react";
import {
    FlatList,
    SafeAreaView,
    StyleSheet,
    Text,
    TouchableOpacity,
    View,
} from "react-native";

const Colors = ({
    colors: {
        item: { colorName, hexCode },
    },
}) => {
    return (
        <View style={{ display: "flex", flexDirection: "row", width: 200 }}>
            <View
                style={{
                    width: 20,
                    height: 20,
                    backgroundColor: hexCode,
                    display: "flex",
                    flexDirection: "row",
                }}
            ></View>
        </View>
    );
};

const Item = ({ item }) => {
    const renderItem = (colors) => {
        return <Colors colors={colors} />;
    };
    const navigation = useNavigation();
    return (
        <TouchableOpacity
            style={[styles.item]}
            onPress={() => navigation.navigate("Details",
            item)}
        >
            <Text style={[styles.title]}>{item.paletteName}</Text>
            <FlatList
                data={item.colors}
                renderItem={renderItem}
                keyExtractor={(item) => item.hexCode}
            />
        </TouchableOpacity>
    );
};

const HomeScreen = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetch("https://demo0945922.mockable.io/colors")
            .then((response) => response.json())
            .then((json) => setData(json.data))
            .catch((error) => console.error(error));
    }, []);

    const renderItem = ({ item }) => {
        return <Item item={item} />;
    };

    return (
        <SafeAreaView>
            <FlatList
                data={data}
                renderItem={renderItem}
                keyExtractor={(item) => item.id}
            />
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    item: {
        padding: 20,
        marginVertical: 8,
        marginHorizontal: 16,
    },
    title: {
        fontSize: 32,
    },
});

export default HomeScreen;
